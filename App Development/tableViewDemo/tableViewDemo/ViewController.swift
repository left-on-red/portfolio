//
//  ViewController.swift
//  tableViewDemo
//
//  Created by Achatz, Thomas on 2/7/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    @IBOutlet weak var tableView: UITableView!
    var programmingLanguages = [
        "Swift",
        "JavaScript",
        "HTML",
        "CSS",
        "C",
        "C++",
        "C#",
        "Objective-C",
        "TypeScript",
        "Python",
        "PHP",
        "Ruby",
        "Perl",
        "Assembly",
        "Cobalt",
        "Rust"
    ]
    
    override func viewDidLoad() {
        super.viewDidLoad();
        // Do any additional setup after loading the view, typically from a nib.
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return programmingLanguages.count;
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell");
        cell?.textLabel?.text = programmingLanguages[indexPath.row];
        return cell!
    }
    
    @IBAction func onClick(_ sender: UIBarButtonItem) {
        let alert = UIAlertController(title: "add", message: nil, preferredStyle: .alert);
        alert.addTextField(configurationHandler: {
            textField in textField.placeholder = "enter item";
        });
        
        let cancelAction = UIAlertAction(title: "cancel", style: .cancel, handler: nil!);
        alert.addAction(cancelAction);
        
        let okAction = UIAlertAction(title: "add", style: .default, handler: {
            action in
            if (alert.textFields?.first?.text != "") {
                let newItem = alert.textFields?.first?.text;
                
            }
            
        });
        alert.addAction(okAction);
    }
}

