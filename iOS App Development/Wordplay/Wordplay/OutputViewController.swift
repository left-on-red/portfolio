//
//  OutputViewController.swift
//  Wordplay
//
//  Created by Achatz, Thomas on 12/20/18.
//  Copyright © 2018 Achatz, Thomas. All rights reserved.
//

import UIKit

class OutputViewController: UIViewController {
    @IBOutlet weak var madlib: UILabel!
    
    var a1 = "";
    var n = "";
    var p = "";
    var n1 = "";
    var a2 = "";
    var n2 = "";
    var a3 = "";
    var a4 = "";
    var pn = "";
    var n3 = "";
    var nu1 = "";
    var s = "";
    var f1 = "";
    var f2 = "";
    var nu2 = "";
        
    override func viewDidLoad() {
        super.viewDidLoad()

        madlib.text = "Pizza was invented by a \(a1) \(n) chef named \(p). To make a pizza, you need to take a lump of \(n1), and make a thin, round \(a2) \(n2). Then you cover it with \(a3) sauce, \(a4) cheese, and fresh chopped \(pn). Next you have to bake it in a very hot \(n3). When it is done, cut it into \(nu1) \(s). Some kids like \(f1) pizza the best, but my favorite is the \(f2) pizza. If I couldm I would eat pizza \(nu2) times a day!";
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
