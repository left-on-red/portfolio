//
//  ViewController.swift
//  cameraDemo
//
//  Created by Achatz, Thomas on 1/3/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    let imagePicker = UIImagePickerController();

    override func viewDidLoad() {
        super.viewDidLoad()
        imagePicker.delegate = self as! UIImagePickerControllerDelegate & UINavigationControllerDelegate
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    @IBAction func onCamera(_ sender: UIButton) {
        
    }
    
    @IBAction func onLibrary(_ sender: UIButton) {
    }
}

