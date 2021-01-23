//
//  ViewController.swift
//  RockPaperScissors
//
//  Created by Achatz, Thomas on 1/4/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var messageLabel: UILabel!
    @IBOutlet weak var robotResult: UIImageView!
    var state = 0;
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    func evaluate() {
        let robotState = Int.random(in: 0...2);
        
        switch(robotState) {
            case 0: robotResult.image = UIImage(named: "rock"); break;
            case 1: robotResult.image = UIImage(named: "paper"); break;
            case 2: robotResult.image = UIImage(named: "scissors"); break;
            default: break;
        }
        
        // robot has rock
        if (robotState == 0) {
            if (state == 0) {
                messageLabel.text = "it's a tie!";
            }
            
            else if (state == 1) {
                messageLabel.text = "you win!";
            }
            
            else if (state == 2) {
                messageLabel.text = "you lose!";
            }
        }
        
        // robot has paper
        else if (robotState == 1) {
            if (state == 0) {
                messageLabel.text = "you lose!";
            }
                
            else if (state == 1) {
                messageLabel.text = "it's a tie!";
            }
                
            else if (state == 2) {
                messageLabel.text = "you win!";
            }
        }
        
        // robot has scissorss
        else if (robotState == 2) {
            if (state == 0) {
                messageLabel.text = "you win!";
            }
                
            else if (state == 1) {
                messageLabel.text = "you lose!";
            }
                
            else if (state == 2) {
                messageLabel.text = "it's a tie!";
            }
        }
    }

    @IBAction func onRock(_ sender: UIButton) {
        state = 0;
        evaluate();
    }
    
    @IBAction func onPaper(_ sender: UIButton) {
        state = 1;
        evaluate();
    }
    
    @IBAction func onScissors(_ sender: UIButton) {
        state = 2;
        evaluate();
    }
}

