//
//  ViewController.swift
//  ZaHunter
//
//  Created by Achatz, Thomas on 3/20/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit;
import MapKit;

class ViewController: UIViewController, CLLocationManagerDelegate {
    @IBOutlet weak var mapView: MKMapView!
    var manager = CLLocationManager();
    var coordinates:CLLocationCoordinate2D!;
    //var location:CLLocation!;
    var pizza:[MKMapItem] = [];
    
    override func viewDidLoad() {
        super.viewDidLoad();
        manager.requestWhenInUseAuthorization();
        manager.delegate = self;
        manager.desiredAccuracy = kCLLocationAccuracyBest;
        manager.startUpdatingLocation();
        mapView.showsUserLocation = true;
        
        self.coordinates = mapView.userLocation.coordinate;
        //location = manager.location;
        //let annotation = MKPointAnnotation();
        //annotation.coordinate = location.coordinate;
        //annotation.title = "you are here";
        //mapView.addAnnotation(annotation);
    }
    
    //func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
    //    location = locations[0];
    //    updateLocation();
        //let annotation = MKPointAnnotation();
        //annotation.coordinate = location.coordinate;
        //annotation.title = "you are here";
        //mapView.addAnnotation(annotation);
    //}
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        if let location = locations.last {
            self.coordinates = location.coordinate;
        }
    }
    
    @IBAction func onZoom(_ sender: UIBarButtonItem) {
        let region = MKCoordinateRegion(center: self.coordinates, span: MKCoordinateSpan(latitudeDelta: 0.05, longitudeDelta: 0.05));
        self.mapView.setRegion(region, animated: true);
    }
    
    @IBAction func onSearch(_ sender: UIBarButtonItem) {
        let request = MKLocalSearch.Request();
        request.naturalLanguageQuery = "pizza";
        let span = MKCoordinateSpan(latitudeDelta: 0.05, longitudeDelta: 0.05);
        let center = self.coordinates;
        request.region = MKCoordinateRegion(center: center!, span: span);
        let search = MKLocalSearch(request: request);
        search.start(completionHandler: {
            (response, error) in
                guard let response = response
                else { return }
            for mapItem in response.mapItems {
                self.pizza.append(mapItem);
                let annotation = MKPointAnnotation();
                annotation.coordinate = mapItem.placemark.coordinate;
                annotation.title = mapItem.name;
                self.mapView.addAnnotation(annotation);
            }
        });
    }
}

/*
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
 }*/
