//
//  ViewController.swift
//  SafariServicesDemo
//
//  Created by Achatz, Thomas on 1/2/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit
import SafariServices

class ViewController: UIViewController {
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    @IBAction func buttonPress(_ sender: UIButton) {
        let website = "https://www.wikipedia.com";
        let url = NSURL(string: website)!
        let safariView = SFSafariViewController(url: url as URL);
        present(safariView, animated: true, completion: nil);
    }
}
