//
//  ViewController.swift
//  mobileMapper
//
//  Created by Achatz, Thomas on 3/19/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit;
import MapKit;

class ViewController: UIViewController, CLLocationManagerDelegate {
    @IBOutlet weak var mapView: MKMapView!
    var manager = CLLocationManager();
    var location:CLLocation!;
    var parks:[MKMapItem] = [];
    
    override func viewDidLoad() {
        super.viewDidLoad();
        manager.requestWhenInUseAuthorization();
        manager.delegate = self;
        manager.desiredAccuracy = kCLLocationAccuracyBest;
        manager.startUpdatingLocation();
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        location = locations[0];
    }
    
    @IBAction func zoomPressed(_ sender: UIBarButtonItem) {
        let span = MKCoordinateSpan(latitudeDelta: 0.05, longitudeDelta: 0.05);
        let center = location.coordinate;
        mapView.centerCoordinate = center;
    }
    
    @IBAction func searchPressed(_ sender: UIBarButtonItem) {
        let request = MKLocalSearch.Request();
        request.naturalLanguageQuery = "park";
        let span = MKCoordinateSpan(latitudeDelta: 0.05, longitudeDelta: 0.05);
        let center = location.coordinate;
        request.region = MKCoordinateRegion(center: center, span: span);
        let search = MKLocalSearch(request: request);
        search.start(completionHandler: {
            (response, error) in
            guard let response = response
            else { return; }
            for mapItem in response.mapItems {
                self.parks.append(mapItem);
                let annotation = MKPointAnnotation();
                annotation.coordinate = mapItem.placemark.coordinate;
                annotation.title = mapItem.name;
                self.mapView.addAnnotation(annotation);
            }
        });
    }
}
