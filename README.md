# Android Localization Helper

Helps to find missing or obsolete translations for android resources.

##Usage: 
    $git clone git://github.com/4e6/android-localization-helper.git
    $cd android-localization-helper
    $./bin/alh.sh /path/to/android/project

##Output:

    [filename]
     *[resource type]
      *[T] [resource name]
      *[O] [resource name]

[T] Resources need to be translated (exists in original xml but not in localized version)  
[O] Obsolete resources (exists in localized version but not in original one)
