#!/bin/sh
#
# Build script. Run from build directory.

cd ../bin

jar -cvmf ../build/MANIFEST.MF ../lib/helper.jar *
cd ../build
java -jar ../lib/proguard.jar @proguard.cfg -verbose
rm ../lib/helper.jar
