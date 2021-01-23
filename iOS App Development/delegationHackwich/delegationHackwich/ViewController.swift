//
//  ViewController.swift
//  delegationHackwich
//
//  Created by Achatz, Thomas on 2/6/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    @IBOutlet weak var pickerView: UIPickerView!
    
    var programmingLanguages = [
        "JavaScript",
        "Swift",
        "C",
        "C++",
        "C#",
        "Python",
        "Java",
        "Ruby",
        "Perl",
        "HTML",
        "CSS",
        "PHP",
        "Objective-C",
        "Lua",
        "TypeScript"
    ]
    
    override func viewDidLoad() {
        super.viewDidLoad();
        pickerView.delegate = self;
        pickerView.dataSource = self;
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1;
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return programmingLanguages.count;
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return programmingLanguages[row];
    }
}
