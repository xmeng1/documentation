import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import org.jsoup.Jsoup

class Asciidoc2PdfCjk extends DefaultTask {
    String docName
    String docLang

    @InputDirectory
    File getSrcDir() {
        return project.file("content/$docName/adoc/$docLang")
    }

    @InputDirectory
    File getSourceCodeDir() {
        return project.file("content/$docName/source")
    }

    @InputDirectory
    File getImagesDir() {
        return project.file("content/$docName/img")
    }

    @OutputDirectory
    File getDstDir() {
        return project.file("$project.buildDir/$docName/$docLang/pdf")
    }

    @TaskAction
    def transform() {
        def dstDocDir = "$project.buildDir/$docName/$docLang/pdf"

        project.copy {
            from "content/$docName/adoc/$docLang"
            into "$dstDocDir"
        }

        project.copy {
            from "content/$docName/source"
            into "$project.buildDir/$docName/source"
        }

        project.copy {
            from "content/$docName/img"
            into "$dstDocDir/img"
        }

        project.copy {
            from "styles"
            into "$dstDocDir/styles"
        }

        project.exec {
            workingDir "$dstDocDir"


            if (System.getProperty('os.name').toLowerCase().contains('windows')) {
                commandLine 'cmd', '/c'

                args 'asciidoctor-pdf -r asciidoctor-pdf-cjk-kai_gen_gothic -a pdf-style=KaiGenGothicCN', "${docName}.adoc ", '-D', dstDocDir
            } else {
                commandLine 'sh'
                args '-c', "asciidoctor-pdf -r asciidoctor-pdf-cjk-kai_gen_gothic -a pdf-style=KaiGenGothicCN ${docName}.adoc -D $dstDocDir"
            }
        }
    }
}