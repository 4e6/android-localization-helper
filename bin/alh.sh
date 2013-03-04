#!/bin/sh
#
# Start-up script

L10N_COMMAND=readlink

# Mac OS X doesn't support "readlink -f", but Homebrew offers a "coreutils" 
# package that contains "greadlink" (GNU readlink)
if [ `uname` = 'Darwin' ]; then
    L10N_COMMAND=greadlink
    if [ -z `which greadlink` ]; then
        echo "greadlink not found. Please install it using homebrew:" 1>&2
        echo "brew install coreutils" 1>&2
        exit 1
    fi
fi

L10N_HELPER_HOME="$(dirname "$($L10N_COMMAND -f "$0")")/.."

java -jar $L10N_HELPER_HOME/lib/l10n-helper.jar $1 $2
