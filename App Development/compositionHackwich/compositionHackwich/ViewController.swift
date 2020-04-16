//
//  ViewController.swift
//  compositionHackwich
//
//  Created by Achatz, Thomas on 3/11/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var nameField: UITextField!
    @IBOutlet weak var descriptionField: UITextField!
    @IBOutlet weak var salaryField: UITextField!
    @IBOutlet weak var outputView: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        if let name = nameField.text, let description = descriptionField.text, let salary = salaryField.text {
            if let salaryInt = Int(salary) {
                let job = Job(desc: description, money: salaryInt);
                let person = Person(n: name, j: job);
                let output = "name: \(person.name)\ndescription: \(person.job.description)\nsalary: \(person.job.salary)";
                outputView.text = output;
            }
        }
        
        return true;
    }
}
