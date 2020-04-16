//
//  ViewController.swift
//  Wordplay
//
//  Created by Achatz, Thomas on 12/13/18.
//  Copyright © 2018 Achatz, Thomas. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var a1: UITextField!
    @IBOutlet weak var n: UITextField!
    @IBOutlet weak var p: UITextField!
    @IBOutlet weak var n1: UITextField!
    @IBOutlet weak var a2: UITextField!
    @IBOutlet weak var n2: UITextField!
    @IBOutlet weak var a3: UITextField!
    @IBOutlet weak var a4: UITextField!
    @IBOutlet weak var pn: UITextField!
    @IBOutlet weak var n3: UITextField!
    @IBOutlet weak var nu1: UITextField!
    @IBOutlet weak var s: UITextField!
    @IBOutlet weak var f1: UITextField!
    @IBOutlet weak var f2: UITextField!
    @IBOutlet weak var nu2: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let dvc = segue.destination as! OutputViewController;
        
        dvc.a1 = a1.text ?? "";
        dvc.n = n.text ?? "";
        dvc.p = p.text ?? "";
        dvc.n1 = n1.text ?? "";
        dvc.a2 = a2.text ?? "";
        dvc.n2 = n2.text ?? "";
        dvc.a3 = a3.text ?? "";
        dvc.a4 = a4.text ?? "";
        dvc.pn = pn.text ?? "";
        dvc.n3 = n3.text ?? "";
        dvc.nu1 = nu1.text ?? "";
        dvc.s = s.text ?? "";
        dvc.f1 = f1.text ?? "";
        dvc.f2 = f2.text ?? "";
        dvc.nu2 = nu2.text ?? "";
    }

    /*override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let dvc = segue.destination as! OutputViewController;
        dvc.madlib.text = "Pizza was invented by a \(a1.text) \(n.text) chef named \(p.text). To make a pizza, you need to take a lump of \(n1.text), and make a thin, round \(a2.text) \(n2.text). Then you cover it with \(a3.text) sauce, \(a4.text) cheese, and fresh chopped \(pn.text). Next you have to bake it in a very hot \(n3.text). When it is done, cut it into \(nu1.text) \(s.text). Some kids like \(f1.text) pizza the best, but my favorite is the \(f2.text) pizza. If I couldm I would eat pizza \(nu2.text) times a day!";
    }*/
}

