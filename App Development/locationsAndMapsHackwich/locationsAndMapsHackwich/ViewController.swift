//
//  ViewController.swift
//  locationsAndMapsHackwich
//
//  Created by Achatz, Thomas on 3/18/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit
// import CoreLocation
import MapKit

class ViewController: UIViewController, CLLocationManagerDelegate, MKMapViewDelegate {
    @IBOutlet weak var textView: UITextView!
    @IBOutlet weak var mapView: MKMapView!
    
    let manager = CLLocationManager();
    
    override func viewDidLoad() {
        super.viewDidLoad();
        manager.delegate = self;
        manager.requestWhenInUseAuthorization();
        manager.startUpdatingLocation();
        
        mapView.delegate = self;
        mapView.showsUserLocation = true;
        mapView.userLocation.title = "you are here";
        addPinAnnotationToMapView();
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        if let location = locations.first {
            textView.text = "\(location)";
        }
    }
    
    func addPinAnnotationToMapView() {
        let coordinate = CLLocationCoordinate2D(latitude: 37, longitude: -122);
        let annotation = MKPointAnnotation();
        annotation.coordinate = coordinate;
        annotation.title = "title";
        annotation.subtitle = "subtitle";
        mapView.addAnnotation(annotation);
    }
}
