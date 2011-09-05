#!/bin/sh
#
# Start-up script

L10N_HELPER_HOME="$(dirname "$(readlink -f "$0")")/.."

java -jar $L10N_HELPER_HOME/lib/l10n-helper.jar $1
