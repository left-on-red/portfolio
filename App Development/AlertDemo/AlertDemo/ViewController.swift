//
//  ViewController.swift
//  AlertDemo
//
//  Created by Achatz, Thomas on 12/5/18.
//  Copyright © 2018 Achatz, Thomas. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var label: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad();
        label.text = "";
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    @IBAction func onButton(_ sender: UIButton) {
        let alert = UIAlertController(title: "enter your name", message: nil, preferredStyle: .alert);
        let cancelAction = UIAlertAction(title: "cancel", style: .cancel);
        alert.addAction(cancelAction);
        
        let okAction = UIAlertAction(title: "Ok", style: .default);
        alert.addAction(okAction);
        
        if let firstname = alert.
        
        //present(alert, animated: true, completion: nil);
    }
}

