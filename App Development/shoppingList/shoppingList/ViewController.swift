//
//  ViewController.swift
//  shoppingList
//
//  Created by Achatz, Thomas on 2/19/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    @IBOutlet weak var newItemTextField: UITextField!
    
    @IBOutlet weak var tableView: UITableView!
    var items:[Item] = [];
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.delegate = self;
        tableView.dataSource = self;
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return items.count;
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell as! UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "protoCell");
        cell!.textLabel?.text = items[indexPath.row].name;
        return cell;
    }
    
    @IBAction func addItem(_ sender: UIBarButtonItem) {
        if (newItemTextField.text != "") {
            items.append(Item(ItemName: newItemTextField.text!));
            newItemTextField.text = "";
            tableView.reloadData();
        }
    }
}

//tableView.reloadData
