#!/bin/sh
#
# Start-up script

L10N_HELPER_HOME=`dirname "$0"`
L10N_HELPER_HOME=`dirname "$L10N_HELPER_HOME"`

java -jar $L10N_HELPER_HOME/lib/l10n-helper.jar $1
