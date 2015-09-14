# CacheUtilsLibrary 中文版

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CacheUtilsLibrary-green.svg?style=flat)](https://android-arsenal.com/details/1/2478)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.lifeofcoding/cacheutilslibrary/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.lifeofcoding/cacheutilslibrary)

这是一个将任何Java Object类型的数据序列化后写入缓存文件，将来使用时读取缓存文件并反序列化成对应Java Object的库。  

##Gradle
```Groovy
compile 'com.lifeofcoding:cacheutilslibrary:1.0.0@aar'
```

如果编辑时有下面的错误：

```
duplication file during packaging of APK ...
Path in archive: META-INF/LICENSE.txt
...
```
请在 `build.gradle` 文件的 `android` 节点添加如下代码：

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

##配置
你需要在项目的 `Application` 类中配置一下 `CacheUtilsLibrary` 库。

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
别忘了在 `AndroidManifest.xml` 文件中申明这个 `MyApplication` 类。


##使用
所有样例代码都能在 [MainActivity](https://github.com/westlinkin/CacheUtilsLibrary/blob/master/sample/src/main/java/com/lifeofcoding/cacheutilslibrary_sample/MainActivity.java) 类中找到。

#####缓存文件内容为 `String` 
```Java
// write
CacheUtils.writeFile(CACHE_FILE_STRING, CACHE_FILE_CONTENT_STRING);

// read
String fileContent = CacheUtils.readFile(CACHE_FILE_STRING);
```

#####缓存文件内容为 `Map<String, T>` 
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

#####缓存文件内容为 `List<Map<String, T>`
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
#####缓存文件内容为 `Object`
```Java
// write
CacheUtils.writeObjectFile(CACHE_FILE_OBJECT, MyClass.SAMPLE_MYCLASS_1);

// read
MyClass myClassSample = CacheUtils.readObjectFile(CACHE_FILE_OBJECT, new TypeToken<MyClass>(){}.getType());
```
你能在[这里](https://github.com/westlinkin/CacheUtilsLibrary/blob/master/sample/src/main/java/com/lifeofcoding/cacheutilslibrary_sample/MyClass.java) and [这里](https://github.com/westlinkin/CacheUtilsLibrary/blob/master/sample/src/main/java/com/lifeofcoding/cacheutilslibrary_sample/MyClass.java#L46)分别找到`MyClass` 和 `MyClass.SAMPLE_MYCLASS_1` 。

#####缓存文件内容为 `List<Object>`
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

##开源协议

	Copyright 2015 Wesley Lin

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

    	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.

