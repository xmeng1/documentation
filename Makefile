# If you system jave is not Java8 (such as you are using java 11, should specify the java 8 home path)
#   current wrapped gradle version cannot support java version > 8
JAVA_HOME=${HOME}/.sdkman/candidates/java/8.0.252-zulu
clean:
	./gradlew clean

manual-en-pdf:
	./gradlew buildManualEnPdf --debug

manual-ru-pdf:
	./gradlew buildManualRuPdf --debug

manual-en-html-single:
	./gradlew buildManualEn --debug

# CHS

sync-chs:
	cp -rf ../cn/documentation/content/manual/adoc/chs content/manual/adoc

manual-chs-pdf:
	./gradlew buildManualChsPdf --debug


manual-chs-html-single:
	./gradlew buildManualChs --debug
##
asciidoctor-pdf -a pdf-theme=basic-theme.yml -a pdf-fontsdir="/Users/mengxin/Library/Fonts" manual.adoc -v --trace

asciidoctor-pdf -r asciidoctor-pdf-cjk-kai_gen_gothic -a pdf-style=KaiGenGothicCN manual.adoc -v --trace
