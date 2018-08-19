#!/bin/bash

MYDIR=$(cd $(dirname $0); pwd -P)
CHUNKER_DIR=/home/lsiqueira/Dropbox/Documentos/IFF\ Sistemas/Projetos/Projeto\ de\ Pesquisa\ IC\ em\ educacao\ prototipo/ProjetoEAD-Web/POSTagger/Chunker
TOKENIZER_DIR=/home/lsiqueira/Dropbox/Documentos/IFF\ Sistemas/Projetos/Projeto\ de\ Pesquisa\ IC\ em\ educacao\ prototipo/ProjetoEAD-Web/POSTagger/Tokenizer
TAGGER_DIR=/home/lsiqueira/Dropbox/Documentos/IFF\ Sistemas/Projetos/Projeto\ de\ Pesquisa\ IC\ em\ educacao\ prototipo/ProjetoEAD-Web/POSTagger/Tagger

# Run pipeline
iconv -f UTF-8 -t ISO-8859-1 -c |
$CHUNKER_DIR/run-Chunker.sh 2>/dev/null |
$TOKENIZER_DIR/run-Tokenizer.sh 2>/dev/null |
$TAGGER_DIR/run-Tagger.sh 2>/dev/null |
/home/lsiqueira/Dropbox/Documentos/IFF\ Sistemas/Projetos/Projeto\ de\ Pesquisa\ IC\ em\ educacao\ prototipo/ProjetoEAD-Web/POSTagger/Tokenizer/post-tok.pl |
iconv -f ISO-8859-1 -t UTF-8 -c
