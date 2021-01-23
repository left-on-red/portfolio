//
//  ViewController.swift
//  VariablesAndAssignmentChallenge
//
//  Created by Robin Roberts on 5/12/16.
//  Copyright (c) 2016 MobileMakers. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var mvpLabel: UILabel!
    var firstName:String = ""
    
    @IBOutlet weak var stretchOneLabel: UILabel!
    @IBOutlet weak var stretchTwoLabel: UILabel!
    @IBOutlet weak var stretchThreeLabel: UILabel!
    
    /***************************************************
     * Start Your Code Here For Stretch #1 - Part I
     ***************************************************/

    var titleStr:String = "Mr."
    var lastName:String = "Achatz"

    /***************************************************
     * End Your Code Here For Stretch #1 - Part I
     ***************************************************/
    
    /***************************************************
     * Start Your Code Here For Stretch #2 - Part I
     ***************************************************/
    
    var nameOne:String = "Bob"
    var nameTwo:String = "Chris"
    var nameThree:String = "Crystal"
    var nameFour:String = "Xavier"
    
    /***************************************************
     * End Your Code Here For Stretch #2 - Part I
     ***************************************************/
    
    /***************************************************
     * Start Your Code Here For Stretch #3 - Part I
     ***************************************************/
    
    var scoreOne:Int = 52
    var scoreTwo:Int = 156
    var scoreThree:Int = 221
    var scoreFour:Int = 349
    
    /***************************************************
     * End Your Code Here For Stretch #3 - Part I
     ***************************************************/
    
    override func viewDidLoad() {
        super.viewDidLoad()
        /***************************************************
         * Start Your Code Here For MVP
         ***************************************************/
        firstName = "Thomas"
        /***************************************************
         * End Your Code Here For MVP
         ***************************************************/
        mvpLabel.text = firstName
        
        /***************************************************
         * Start Your Code Here For Stretch #1 - Part II
         ***************************************************/
        
        stretchOneLabel.text = "Hello \(titleStr) \(lastName)"
        
        /***************************************************
         * End Your Code Here For Stretch #1 - Part II
         ***************************************************/
        
        //Uncomment the line below for Stretch #1
        //stretchOneLabel.text = greeting
        
        /***************************************************
         * Start Your Code Here For Stretch #2 - Part II
         ***************************************************/
        
        /***************************************************
         * End Your Code Here For Stretch #2 - Part II
         ***************************************************/
        
        
        let teamOne:String = "\(nameOne), \(nameTwo)"
        let teamTwo:String = "\(nameThree), \(nameFour)"
        
        stretchTwoLabel.text = "Team 1: \(teamOne)\nTeam 2: \(teamTwo)"
        
        /***************************************************
         * Start Your Code Here For Stretch #3 - Part II
         ***************************************************/
        
        let teamOneScores:String = "\(nameOne): \(scoreOne) & \(nameTwo): \(scoreTwo)"
        let teamTwoScores:String = "\(nameThree): \(scoreThree) & \(nameFour): \(scoreFour)"
        
        stretchThreeLabel.text = "\(teamOneScores)\n\(teamTwoScores)"
        
        /***************************************************
         * End Your Code Here For Stretch #3 - Part II
         ***************************************************/
        
        //Uncomment the line below for Stretch #1
        //stretchThreeLabel.text = "\(teamHighScoreOne)\n\(teamHighScoreTwo)"
    }
}
