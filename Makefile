# gem install asciidoctor asciidoctor-diagram coderay
docs/index.html: book.adoc
	asciidoctor -o docs/index.html -b html5 -r asciidoctor-diagram book.adoc 
