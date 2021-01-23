//
//  ViewController.swift
//  PersistenceChallenge
//
//  Created by Bobb Darnell on 5/2/18.
//  Copyright © 2018 MobileMakersEdu. All rights reserved.
//

import UIKit

class StoreViewController: UIViewController {

    @IBOutlet weak var integerTextField: UITextField!
    @IBOutlet weak var urlTextField: UITextField!
    @IBOutlet weak var arrayTextField1: UITextField!
    @IBOutlet weak var arrayTextField2: UITextField!
    @IBOutlet weak var arrayTextField3: UITextField!
    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var ageTextField: UITextField!
    @IBOutlet weak var phoneTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    @IBAction func saveInteger(_ sender: UIButton) {
        /***************************************************
         * Start Your Code Here For MVP
         ***************************************************/
        let int = Int(integerTextField.text!);
        let defaults = UserDefaults.standard;
        defaults.set(int, forKey: "int");
        /***************************************************
         * End Your Code Here For MVP
         ***************************************************/
        integerTextField.resignFirstResponder()
    }
    
    @IBAction func saveUrl(_ sender: UIButton) {
        /***************************************************
         * Start Your Code Here For Stretch #1
         ***************************************************/
        let url = URL(string: urlTextField.text!);
        let defaults = UserDefaults.standard;
        defaults.set(url, forKey: "url");
        /***************************************************
         * End Your Code Here For Stretch #1
         ***************************************************/
        urlTextField.resignFirstResponder()
    }
    
    @IBAction func saveArray(_ sender: UIButton) {
        /***************************************************
         * Start Your Code Here For Stretch #2
         ***************************************************/
        let array = [arrayTextField1.text, arrayTextField2.text, arrayTextField3.text];
        let defaults = UserDefaults.standard;
        defaults.set(array, forKey: "array");
        /***************************************************
         * End Your Code Here For Stretch #2
         ***************************************************/
        arrayTextField1.resignFirstResponder()
        arrayTextField2.resignFirstResponder()
        arrayTextField3.resignFirstResponder()
    }
    
    @IBAction func saveClass(_ sender: UIButton) {
        /***************************************************
         * Start Your Code Here For Stretch #3
         ***************************************************/
        
        /***************************************************
         * End Your Code Here For Stretch #3
         ***************************************************/
        nameTextField.resignFirstResponder()
        ageTextField.resignFirstResponder()
        phoneTextField.resignFirstResponder()
    }
}

