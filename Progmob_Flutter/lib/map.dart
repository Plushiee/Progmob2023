import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_map_location_marker/flutter_map_location_marker.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:location/location.dart';
import 'package:maps_launcher/maps_launcher.dart';
import 'package:sliding_up_panel/sliding_up_panel.dart';

class MyMap extends StatefulWidget {
  @override
  _MyMapState createState() => _MyMapState();
}

class _MyMapState extends State<MyMap> {
  LocationData? currentLocation;
  Location location = Location();

  late FollowOnLocationUpdate _followOnLocationUpdate;
  late StreamController<double?> _followCurrentLocationStreamController;

  // Dataset
  final String data1 =
      "Ex assueverit mel sed posidonium hinc laudem option iuvaret. Leo taciti ornare epicurei inceptos elementum ornatus neque molestie. "
      "Tation tellus iudicabit vero movet sodales appareat constituam nullam. Eam purus porttitor constituto atomorum legimus porttitor. "
      "Te pellentesque harum potenti nobis.";
  final String data2 =
      "Hinc elaboraret prodesset litora parturient postea sadipscing ex. Lacus ipsum autem quo wisi inani. Harum sea eripuit ea ancillae "
      "saperet sapientem iuvaret assueverit. "
      "Convenire mazim persequeris indoctum constituam. Lectus eleifend tortor te habemus hac tantas.";
  final String data3 =
      "Ocurreret necessitatibus urbanitas nulla maiestatis litora solet molestiae. In possit curabitur imperdiet "
      "metus luptatum electram deseruisse eos quod. Dolorem urbanitas commodo reprehendunt gubergren inimicus.";
  final String data4 =
      "Persius massa semper vix indoctum ius definiebas nonumes. Ridens taciti arcu mus orci himenaeos nibh. "
      "Interpretaris graeci porttitor dignissim verear.";
  late String dataSlide;
  late double long;
  late double lat;
  List<Marker> allMarkers = [];

  @override
  void initState() {
    super.initState();

    // inisialisasi awal
    _getLocation();
    _followOnLocationUpdate = FollowOnLocationUpdate.always;
    _followCurrentLocationStreamController = StreamController<double?>();

    //  init slidingup data
    dataSlide = data1;

    //  inisialisasi marker
    allMarkers.add(
      Marker(
        width: 100.0,
        height: 100.0,
        point: const LatLng(-7.772883015693774, 110.35162041008356),
        // Example location 1
        child: IconButton(
          onPressed: () {
            setState(() {
              dataSlide = data1;
              lat = -7.772883015693774;
              long = 110.35162041008356;
            });
          },
          icon: const Icon(Icons.pin_drop, color: Colors.black),
        ), // Customize the marker
      ),
    );

    allMarkers.add(
      Marker(
        width: 100.0,
        height: 100.0,
        point: const LatLng(-7.776925950151403, 110.417587608133),
        // Example location 1
        child: IconButton(
          onPressed: () {
            setState(() {
              dataSlide = data2;
              lat = -7.776925950151403;
              long = 110.417587608133;
            });
          },
          icon: const Icon(Icons.pin_drop, color: Colors.black),
        ), // Customize the marker
      ),
    );

    allMarkers.add(
      Marker(
        width: 100.0,
        height: 100.0,
        point: const LatLng(-7.810447112334517, 110.3317282395377),
        // Example location 1
        child: IconButton(
          onPressed: () {
            setState(() {
              dataSlide = data3;
              lat = -7.810447112334517;
              long = 110.3317282395377;
            });
          },
          icon: const Icon(Icons.pin_drop, color: Colors.black),
        ), // Customize the marker
      ),
    );

    allMarkers.add(
      Marker(
        width: 100.0,
        height: 100.0,
        point: const LatLng(-7.814152804539516, 110.39514515931207),
        // Example location 1
        child: IconButton(
          onPressed: () {
            setState(() {
              dataSlide = data4;
              lat = -7.814152804539516;
              long = 110.39514515931207;
            });
          },
          icon: const Icon(Icons.pin_drop, color: Colors.black),
        ), // Customize the marker
      ),
    );
  }

  Future<void> _getLocation() async {
    try {
      currentLocation = await location.getLocation();
      setState(() {});
    } catch (e) {
      if (kDebugMode) {
        print('Error getting location: $e');
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          if (currentLocation != null)
            FlutterMap(
              options: MapOptions(
                initialCenter: LatLng(
                  currentLocation!.latitude ?? 0.0,
                  currentLocation!.longitude ?? 0.0,
                ),
                initialZoom: 10.0,
              ),
              children: [
                TileLayer(
                  urlTemplate: 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
                  subdomains: const ['a', 'b', 'c'],
                  tileSize: 256,
                ),
                CurrentLocationLayer(
                  followOnLocationUpdate: _followOnLocationUpdate,
                ),
                MarkerLayer(markers: allMarkers)
              ],
            ),
          SlidingUpPanel(
            backdropEnabled: true,
            panel: Column(
              children: [
                Text(dataSlide),
                ElevatedButton(
                  onPressed: () {
                    MapsLauncher.launchCoordinates(lat, long);
                  },
                  child: Text("Navigasi"),
                ),
              ],
            ),
          ),
          Positioned(
            bottom: 16,
            right: 16,
            child: FloatingActionButton(
              onPressed: () {
                setState(
                  () => _followOnLocationUpdate = FollowOnLocationUpdate.always,
                );
              },
              child: const Icon(
                Icons.my_location,
                color: Colors.white,
              ),
            ),
          ),
          if (currentLocation == null)
            const Center(
              child: CircularProgressIndicator(),
            ),
        ],
      ),
    );
  }
}
