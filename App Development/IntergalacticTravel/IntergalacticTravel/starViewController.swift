//
//  starViewController.swift
//  IntergalacticTravel
//
//  Created by Achatz, Thomas on 12/11/18.
//  Copyright © 2018 Achatz, Thomas. All rights reserved.
//

import UIKit

class starViewController: UIViewController {
    @IBOutlet weak var starImage: UIImageView!
    var blueStar: Bool = true;
    
    override func viewDidLoad() {
        super.viewDidLoad()

        if (blueStar) {
            self.view.backgroundColor = UIColor.blue;
            starImage.image = UIImage(named: "blue");
        }
        
        else {
            self.view.backgroundColor = UIColor.red;
            starImage.image = UIImage(named: "red");
        }
        // Do any additional setup after loading the view.
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
