//
//  ViewController.swift
//  mapDemo
//
//  Created by Achatz, Thomas on 3/14/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit;
import MapKit;

class ViewController: UIViewController {
    var map = MKMapView(frame: CGRect(x: 0, y: 0, width: 300, height: 300));
    override func viewDidLoad() {
        super.viewDidLoad();
        
        let location = CLLocationCoordinate2D(latitude: 43.0117, longitude: -88.2315);
        let span = MKCoordinateSpan(latitudeDelta: 1.0, longitudeDelta: 1.0);
        let region = MKCoordinateRegion(center: location, span: span);
        map.setRegion(region, animated: true);
        
        let annotation = MKPointAnnotation();
        annotation.coordinate = location;
        annotation.title = "Waukesha WI";
        annotation.subtitle = "You Live Here";
        
        let request = MKLocalSearch.Request();
        request.naturalLanguageQuery = "University";
        request.region = region;
        let search = MKLocalSearch(request: request);
        search.start(completionHandler: func (response, error) {
            guard let response = response
            else {
                for mapItem in reponse.mapItems {
                    let searchAnnotation = MKPointAnnotation()
                }
            }
        });
        
        view = map;
    }
}

