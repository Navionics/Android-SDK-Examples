# Getting Started

## Step 1:  Get an application Token
Before you can begin working with Navionics Mobile SDK for Android, you need to ensure that you have an application token.

* Login or register a new user on the Navionics Developer Portal
* Go to your developer console
* Select Credentials Management
* Generate and add your SSH key to your account
* Select App Management and add the Bundle Identifier of your app
* As soon as the token request will be processed, you will receive a notification by mail.

## Step 2:  Get Android Studio
To build a project using the Android Studio 2.3 or above is reccomended..


## Step 3:  Install the Navionics Mobile SDK for Android

1. Download the SDK com.navionics.android.nms.aar.
2. Copy com.navionics.android.nms.aar under your project directory under the libs folder.
3. Open your project.
4. Open app level  build.gradle file and  add navionics aar library:
            dependencies {
                compile fileTree(dir: 'libs', include: ['*.jar'])
                compile(name:'com.navionics.android.nms', ext:'aar')
                .....
            }



## Step 4:  Use your Token in your application

Add your token and your private key to your SDK as  follow:


1.  Import the `NavionicsMobileSDK` package com.navionics.android.nms.NavionicsMobileServices


2. Add your token and your private key to your Activity
```java-code
NMSSettings settings = NMSSettings.settings();
settings.setMode(NMSEnum.NMSFrameworkMode.NMSFrameworkModeSandbox);
setProjectToken = ("YOUR-PROJECT-TOKEN");
setPrivateKey("-----BEGIN RSA PRIVATE KEY-----\nYOUR\nPRIVATE\nKEY\n-----END RSA PRIVATE KEY-----\n");
NSError error = new NSError();
NavionicsMobileServices.initializeWithSettings(settings, error);
```
Note that your private key will have the following format:
```
-----BEGIN RSA PRIVATE KEY-----
YOUR
PRIVATE
KEY
-----END RSA PRIVATE KEY-----
```

Do not forget to add the new line character `\n`

How to use the Navionics Mobile SDK
================

## Add a map

You can add the ```NMSMapFragment``` in your layout as follow:

    <fragment android:name="com.navionics.android.nms.ui.NMSMapFragment"
                android:id="@+id/navMap"
                android:layout_width="fill_parent"
                android:layout_height="fill_parent" />

```
Only one map view can be active at any given time. If you activate a new map, the previous one will be deactivated.
```
## Get and search map objects (POIs)
You can get ```NMSGeoObject``` by querying the map in a specific view ```NMSLocationCoordinate2D``` using
```java-code
NavionicsMobileServices.geoObjectsAtPoint(mapViewPoint);
```
Or you can search them by using two kinds of searches. 
By name:
```java-code
NavionicsMobileServices.searchGeoObjectsByName(nameToSearch);
```
and by category:
```java-code
NavionicsMobileServices.searchGeoObjectsByCategory(NMSSearchCategoryRestaurant);
```

Implements your event handler conform to the ```OnSearchWithStatusListListener``` interface.
Set your delegate as geo object results delegate 
```java-code
NavionicsMobileServices.setOnSearchWithStatus(new OnSearchWithStatusListListener(){
            @Override
            public void onSearchWithStatus() {
            //do something
            }
});
```
to get the results of the map querying and the search.
All the results will contains icon and name. If you need more detailed information, you can load the object details by using
```java-code
NMSGeoObject.setOnComplete(new NMSGeoObject.OnCompleteListener() {
    @Override
    public void onComplete() {
        //do something
    }
});
```

## Add your own content
You can use the following overlay objects to add your own content on the map
```java-code
NMSCircle
NMSPolygon
NMSPolyline
NMSMarker
NMSGroundOverlay
```
Create your object and set its ```map```.

* Add a ```NMSCircle```.
    ```java-code
    NMSCircle circle=NMSCircle.circleWithPosition(new NMSLocationCoordinate2D(41.814092,-70.5326246),250);
    circle.setFillColor(Color.RED);
    circle.setZIndex(1);
    circle.setMap(view);
    circle.setStrokeWidth(2);
    circle.setStrokeColor(NMSColor.argb(0.5f,0.5f,0.2f,0.8f));
     ```

* Add a ```NMSPolygon```.
	```java-code
	NMSMutablePath path = new NMSMutablePath();
	path.addCoordinate(new NMSLocationCoordinate2D(41.5943782,-70.3538668));
	path.addCoordinate(new NMSLocationCoordinate2D(41.573444,-70.3862691));
	path.addCoordinate(new NMSLocationCoordinate2D(41.5528769,-70.3494521));
	path.addCoordinate(new NMSLocationCoordinate2D(41.5624133,-70.3352917));
	NMSPolygon polygon=NMSPolygon.polygonWithPath(path);
	polygon.setFillColor(NMSColor.rgba(75.0f/255.0f,159.0f/255.0f,101.0f/255.0f,0.7f));
	polygon.setStrokeWidth(5.0f);
	polygon.setStrokeColor(NMSColor.colorWithAlphaComponent(Color.BLACK,0.5f));
	polygon.setMap(view);
	```

* Add a ```NMSMarker```.
	```java-code
	NMSLocationCoordinate2D markerLocation = new NMSLocationCoordinate2D(41.6229011,-70.285697);
	NMSMarker marker= NMSMarker.markerWithPosition(markerLocation);
	marker.setImage(R.drawable.marker_anchor);
	marker.setMap(view);
	mMarkers.add(marker);
	marker.setAnchor(CGPoint.CGPointMake(0.5f, 0.5f));
	marker.setOpacity(0.8f);
	```

* Add a ```NMSPolyline```.
	```java-code
	NMSMutablePath path = new NMSMutablePath();
	
	path.addCoordinate(new NMSLocationCoordinate2D(41.5698391,-70.0216346));
	path.addCoordinate(new NMSLocationCoordinate2D(41.5417418,-70.0273104));
	path.addCoordinate(new NMSLocationCoordinate2D(41.5208243,-69.9901994));
	path.addCoordinate(new NMSLocationCoordinate2D(41.5396177,-69.9076818));
	NMSPolyline polyline= NMSPolyline.polylineWithPath(path);
	polyline.setStrokeColor(NMSColor.colorWithAlphaComponent(Color.GREEN,0.5f));
	polyline.setStrokeWidth (5.0f);
	polyline.setMap(view);
	```

* Add a ```NMSGroundOverlay```.
	```java-code
	NMSCoordinateBounds bounds = NMSCoordinateBounds.initWithCoordinate( new NMSLocationCoordinate2D(41.0,-70.0), new NMSLocationCoordinate2D(41.1,-70.1));
	NMSGroundOverlay overlay = NMSGroundOverlay.groundOverlayWithBounds(bounds,R.drawable.marker_anchor);
	overlay.setMap(view);
	overlay.setOpacity(0.8f);

	```
	
	
	

