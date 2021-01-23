    //
//  ViewController.swift
//  assignmentNotebook
//
//  Created by Achatz, Thomas on 2/25/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit

    class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
        
    @IBOutlet weak var tableView: UITableView!
    var items:[String] = [];
    
    override func viewDidLoad() {
        let defaults = UserDefaults.standard;
        if ((defaults.array(forKey: "items")) != nil) {
            items = defaults.array(forKey: "items") as! [String];
        }
    }

    @IBAction func addItem(_ sender: UIBarButtonItem) {
        let alert = UIAlertController(title: "add assignment", message: nil, preferredStyle: .alert);
        alert.addTextField(configurationHandler: nil);
        alert.textFields![0].placeholder = "enter an item";
        
        let addAction = UIAlertAction(title: "add", style: .default) {
            UIAlertAction in
            self.addToList(name: alert.textFields![0].text!);
        }
        
        let cancelAction = UIAlertAction(title: "cancel", style: .cancel);
        
        alert.addAction(addAction);
        alert.addAction(cancelAction);
        self.present(alert, animated: false, completion: nil);
    }
    
    func addToList(name:String) {
        items.append(name);
        let defaults = UserDefaults.standard;
        defaults.set(items, forKey: "items");
        tableView.reloadData();
    }
        
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return items.count;
    }
        
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "protoCell");
        cell?.textLabel?.text = items[indexPath.row];
        
        return cell!;
    }
        
        
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if (editingStyle == .delete) {
            self.items.remove(at: indexPath.row);
            let defaults = UserDefaults.standard;
            defaults.set(self.items, forKey: "items");
            tableView.deleteRows(at: [indexPath], with: .fade);
        }
    }
}
