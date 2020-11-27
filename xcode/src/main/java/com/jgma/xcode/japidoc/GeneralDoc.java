package com.jgma.xcode.japidoc;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * @Author: admin
 */
public class GeneralDoc {
    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        // root project path
        config.setProjectPath("D:\\gitSource\\magic-code\\xcode");
        // project name
        config.setProjectName("xcode");
        // api version
        config.setApiVersion("japidoc");
        // api docs target path
        config.setDocsPath("D:\\gitSource\\magic-code\\xcode\\src\\main\\resources\\static");
        // auto generate
        config.setAutoGenerate(Boolean.TRUE);
        // execute to generate
        Docs.buildHtmlDocs(config);
    }
}
