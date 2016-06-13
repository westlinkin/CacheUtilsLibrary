

# CacheUtilsLibrary

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CacheUtilsLibrary-green.svg?style=flat)](https://android-arsenal.com/details/1/2478)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.lifeofcoding/cacheutilslibrary/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.lifeofcoding/cacheutilslibrary)
[![Android Gems](http://www.android-gems.com/badge/westlinkin/CacheUtilsLibrary.svg?branch=master)](http://www.android-gems.com/lib/westlinkin/CacheUtilsLibrary)

This is a simple Android utils library to write any type of data into cache files and then read them later, using `Gson` to serialize and deserialize these data.

中文版请看[这里](https://github.com/westlinkin/CacheUtilsLibrary/blob/master/README_zhCN.md)。

##Gradle
```Groovy
compile 'com.lifeofcoding:cacheutilslibrary:1.1.0@aar'
compile 'com.google.code.gson:gson:2.2.2'
compile 'commons-io:commons-io:2.4'
```

If you have errors like:

```
duplication file during packaging of APK ...
Path in archive: META-INF/LICENSE.txt
...
```
Please add such code in your `android` entry of your `build.gradle` file:

```Groovy
packagingOptions {
    exclude 'META-INF/DEPENDENCIES'
    exclude 'META-INF/NOTICE'
    exclude 'META-INF/LICENSE'
    exclude 'META-INF/LICENSE.txt'
    exclude 'META-INF/NOTICE.txt'
    exclude 'META-INF/ASL2.0'
    exclude 'META-INF/MANIFEST.MF'
}
```

##Configuration
You need to configure `CacheUtilsLibrary` in your `Application` class.

```Java
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // configure CacheUtilsLibrary
        CacheUtils.configureCache(this);
    }
}
```

Don't forget to declare the `MyApplication` class in your `AndroidManifest.xml` file.


##Usage
All sample code can be found in the [MainActivity](https://github.com/westlinkin/CacheUtilsLibrary/blob/master/sample/src/main/java/com/lifeofcoding/cacheutilslibrary_sample/MainActivity.java) file. 
#####Cache `String` File
```Java
// write
CacheUtils.writeFile(CACHE_FILE_STRING, CACHE_FILE_CONTENT_STRING);

// read
String fileContent = CacheUtils.readFile(CACHE_FILE_STRING);
```

#####Cache `Map<String, T>` File
```Java
// write
CacheUtils.writeDataMapFile(CACHE_FILE_MAP, getCacheFileContentMap());

// read
Map<String, Object> mapData = CacheUtils.readDataMapFile(CACHE_FILE_MAP);

// get mapData
private static Map<String, Object> getCacheFileContentMap() {
    Map<String, Object> mapData = new HashMap<>();
    mapData.put("firstItem", "item0");
    mapData.put("secondItem", 1);
    mapData.put("thirdItem", false);
    return mapData;
}
```

#####Cache `List<Map<String, T>` File
```Java
// write
CacheUtils.writeDataMapsFile(CACHE_FILE_LIST_MAP, getCacheFileContentListMap());

// read
List<Map<String, Object>> listMapData = CacheUtils.readDataMapsFile(CACHE_FILE_LIST_MAP);

// get listMapData
private static List<Map<String, Object>> getCacheFileContentListMap() {
    List<Map<String, Object>> listMapData = new ArrayList<Map<String, Object>>();

    for (int i = 0; i < 4; i++) {
        Map<String, Object> item = new HashMap<>();
        item.put("firstItemAt" + i, "item0At" + i);
        item.put("secondItemAt" + i, 1 + i);
        item.put("thirdItemAt" + i, i % 2 == 0);
        listMapData.add(item);
    }
    return listMapData;
}
```
#####Cache `Object` File
```Java
// write
CacheUtils.writeObjectFile(CACHE_FILE_OBJECT, MyClass.SAMPLE_MYCLASS_1);

// read
MyClass myClassSample = CacheUtils.readObjectFile(CACHE_FILE_OBJECT, new TypeToken<MyClass>(){}.getType());
```
You can see `MyClass` and `MyClass.SAMPLE_MYCLASS_1` [here](https://github.com/westlinkin/CacheUtilsLibrary/blob/master/sample/src/main/java/com/lifeofcoding/cacheutilslibrary_sample/MyClass.java) and [here](https://github.com/westlinkin/CacheUtilsLibrary/blob/master/sample/src/main/java/com/lifeofcoding/cacheutilslibrary_sample/MyClass.java#L46).

#####Cache `List<Object>` File
```Java
// write
CacheUtils.writeObjectFile(CACHE_FILE_LIST_OBJECT, getCacheFileContentListObject());

// read
List<MyClass> myClassList = CacheUtils.readObjectFile(CACHE_FILE_LIST_OBJECT, new TypeToken<List<MyClass>>(){}.getType());

// get List<MyClass> data
private static List<MyClass> getCacheFileContentListObject() {
    List<MyClass> listObject = new ArrayList<>();
    listObject.add(MyClass.SAMPLE_MYCLASS_1);
    listObject.add(MyClass.SAMPLE_MYCLASS_2);
    listObject.add(MyClass.SAMPLE_MYCLASS_3);
    return listObject;
}

```

##License

	Copyright 2015-2016 Wesley Lin

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

    	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.

