package com.test.pages;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SeleniumChromeAuthExtensionBuilder {

    private String headerJsCode = "";
    private String baseUrl = "<all_urls>";

    /**
     * Add a header to every request.
     *
     * @param name The name of the header to add.
     * @param value The value of the header.
     */
    public SeleniumChromeAuthExtensionBuilder withStaticHeader(String name, String value) {
        String nameEscaped  = StringEscapeUtils.escapeEcmaScript(name);
        String valueEscaped  = StringEscapeUtils.escapeEcmaScript(value);
        headerJsCode += "headers.push({name: \"" + nameEscaped + "\", value: \"" + valueEscaped + "\"});\n";
        return this;
    }

    /**
     * Add basic auth credentials to every requests.
     *
     * @param username The username for authentication
     * @param password The password for authentication
     */
    public SeleniumChromeAuthExtensionBuilder withBasicAuth(String username, String password) {
        String encodeUserPass = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        return withStaticHeader("Authorization", "Basic " + encodeUserPass);
    }

    /**
     * If you set a base url only requests to urls starting with this base url will be modified.
     * Can contain wildcards (*). The special value <all_urls> matches all urls (default).
     */
    public SeleniumChromeAuthExtensionBuilder withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * Builds the extension as a zip archive.
     * Please delete this file after installing it.
     *
     * @return The generated zip file
     */
    public File build() throws Exception {
        File tempFile = File.createTempFile("selenium-chrome-auth-", ".tmp.zip");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(tempFile));

        // Add manifest.json
        {
            ZipEntry e = new ZipEntry("manifest.json");
            out.putNextEntry(e);
            out.write(generateManifestJson().getBytes(StandardCharsets.UTF_8));
            out.closeEntry();
        }

        // Add background.js
        {
            ZipEntry e = new ZipEntry("background.js");
            out.putNextEntry(e);
            out.write(generateBackroundJs().getBytes(StandardCharsets.UTF_8));
            out.closeEntry();
        }

        out.close();

        return tempFile;
    }

    private String generateBackroundJs() {
        return  "chrome.webRequest.onBeforeSendHeaders.addListener(\n" +
                "    function(e) {\n" +
                "        var headers = e.requestHeaders;\n" +
                headerJsCode +
                "        return { requestHeaders: headers };\n" +
                "    },\n" +
                "    {urls: [\"" + StringEscapeUtils.escapeEcmaScript(baseUrl) + "\"]},\n" +
                "    ['blocking', 'requestHeaders' , 'extraHeaders']\n" +
                ");";
    }

    private String generateManifestJson() {
        return "{\n" +
                "    \"manifest_version\": 2,\n" +
                "    \"name\": \"Authentication for selenium tests\",\n" +
                "    \"version\": \"1.0.0\",\n" +
                "    \"permissions\": [\"<all_urls>\", \"webRequest\", \"webRequestBlocking\"],\n" +
                "    \"background\": {\n" +
                "      \"scripts\": [\"background.js\"]\n" +
                "    }\n" +
                "  }";
    }
}
