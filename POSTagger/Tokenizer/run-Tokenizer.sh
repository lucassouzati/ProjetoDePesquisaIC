#!/bin/bash

MYDIR=$(cd $(dirname $0); pwd -P)

TOKENIZER_BASE_DIR=$MYDIR
TOKENIZER_LIST_DIR=$TOKENIZER_BASE_DIR/Lists

ABBREV=$TOKENIZER_LIST_DIR/abbrev
CLITICS=$TOKENIZER_LIST_DIR/clitics
CONTR=$TOKENIZER_LIST_DIR/contr

$TOKENIZER_BASE_DIR/tokenizer $ABBREV $CLITICS $CONTR |
sed 's/\([0-9]\+\)-/\1 - /g' |
$TOKENIZER_BASE_DIR/no-lo_fix.pl |
tr -s ' '
