//
//  ViewController.swift
//  guessingGame
//
//  Created by Achatz, Thomas on 1/29/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var indicatorLabel: UILabel!
    @IBOutlet weak var numberInput: UITextField!
    let rand = Int.random(in: 1 ... 20);
    var guessCount = 0;
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    @IBAction func onCheck(_ sender: Any) {
        let guess = Int(numberInput.text!);
        if (guess != nil) {
            guessCount += 1;
            if (guess! > 20) {
                indicatorLabel.text = "the number cannot exceed 20";
            }
        
            else if (guess! < 1) {
                indicatorLabel.text = "the number cannot be below 1";
            }
        
            else {
                if (guess! > rand) {
                    indicatorLabel.text = "lower";
                }
                
                else if (guess! < rand) {
                    indicatorLabel.text = "higher";
                }
                
                else {
                    indicatorLabel.text = "correct: took you \(guessCount) guesses";
                }
            }
        }
        
        else {
            indicatorLabel.text = "enter a number";
        }
    }
}

