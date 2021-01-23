//
//  ViewController.swift
//  persistenceHackwich
//
//  Created by Achatz, Thomas on 2/14/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit
class ViewController: UIViewController, UICollectionViewDataSource {
    @IBOutlet weak var collectionView: UICollectionView!
    var reds:[CGFloat] = [];
    var greens:[CGFloat] = [];
    var blues:[CGFloat] = [];
    
    override func viewDidLoad() {
        super.viewDidLoad();
        collectionView.dataSource = self;
        let defaults = UserDefaults.standard;
        if ((defaults.array(forKey: "reds")) != nil) {
            reds = defaults.array(forKey: "reds") as! [CGFloat];
        }
        
        if ((defaults.array(forKey: "greens")) != nil) {
            greens = defaults.array(forKey: "greens") as! [CGFloat];
        }
        
        if ((defaults.array(forKey: "blues")) != nil) {
            blues = defaults.array(forKey: "blues") as! [CGFloat];
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return reds.count;
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "collectionCell", for: indexPath);
        cell.contentView.backgroundColor = UIColor(red: reds[indexPath.row], green: greens[indexPath.row], blue: blues[indexPath.row], alpha: 1);
        return cell;
    }
    
    @IBAction func addButton(_ sender: UIBarButtonItem) {
        reds.append(CGFloat.random(in: 0 ... 1));
        greens.append(CGFloat.random(in: 0 ... 1));
        blues.append(CGFloat.random(in: 0 ... 1));
        collectionView.reloadData();
    }
    
    @IBAction func saveButton(_ sender: UIBarButtonItem) {
        let defaults = UserDefaults.standard;
        defaults.set(reds, forKey: "reds");
        defaults.set(greens, forKey: "greens");
        defaults.set(blues, forKey: "blues");
    }
}
