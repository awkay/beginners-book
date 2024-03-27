# gem install asciidoctor asciidoctor-diagram coderay
docs/book.html: book.adoc
	asciidoctor -o docs/book.html -b html5 -r asciidoctor-diagram book.adoc 
