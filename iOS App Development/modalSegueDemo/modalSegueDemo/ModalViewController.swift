//
//  ModalViewController.swift
//  modalSegueDemo
//
//  Created by Achatz, Thomas on 3/6/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit

class ModalViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad();
        
        // Do any additional setup after loading the view.
    }
    
    @IBAction func colorButton(_ sender: UIButton) {
        func setColor(_ color:UIColor) {
            UIApplication.shared.keyWindow?.tintColor = color;
            self.dismiss(animated: true, completion: nil);
        }
        
        switch(sender.titleLabel!.text) {
            case "Blue": setColor(UIColor.blue);
            case "Green": setColor(UIColor.green);
            case "Purple": setColor(UIColor.purple);
            case "Red": setColor(UIColor.red);
            case "Orange": setColor(UIColor.orange);
            default: setColor(UIColor.yellow);
        }
    }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
