//
//  ViewController.swift
//  swiftReview
//
//  Created by Achatz, Thomas on 1/24/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var image: UIImageView!
    @IBOutlet weak var label: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var nameField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        nameLabel.text = "";
        // Do any additional setup after loading the view, typically from a nib.
    }

    @IBAction func onClick(_ sender: UIButton) {
        if (label.text == "I M A G E   1") {
            image.image = UIImage(named: "js");
            label.text = "I M A G E   2";
        }
        
        else if (label.text == "I M A G E   2") {
            image.image = UIImage(named: "swift");
            label.text = "I M A G E   1";
        }
        
        nameLabel.text = "Hello \(nameField.text)"
    }
}

