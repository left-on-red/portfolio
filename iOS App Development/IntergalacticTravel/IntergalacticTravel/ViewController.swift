//
//  ViewController.swift
//  IntergalacticTravel
//
//  Created by Achatz, Thomas on 12/10/18.
//  Copyright © 2018 Achatz, Thomas. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let dvc = segue.destination as! starViewController;
        let id = segue.identifier;
        
        if (id == "redSegue") {
            dvc.blueStar = false;
        }
        
        else if (id == "blueSegue") {
            dvc.blueStar = true;
        }
    }
}
