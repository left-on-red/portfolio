//
//  ViewController.swift
//  FunctionsChallenge
//
//  Created by Bob Roberts on 5/17/16.
//  Copyright (c) 2016 MobileMakers. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var myTextView: UITextView!
    var output1 = ""
    var output2 = ""
    var output3 = ""
    var output4 = ""
    var output5 = ""
    var output6 = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    @IBAction func onSliderMoved(_ sender: UISlider) {
        let sliderValue:Int = (Int)(sender.value)

        switch sliderValue {
            case 1:
                /***************************************************
                * Start Your Code Here For MVP
                ***************************************************/
                displayMVP()
                /***************************************************
                * End Your Code Here For MVP
                ***************************************************/
                break
            case 2:
                //Uncomment the line below for Stretch #1
                output1 = returnAString()
                myTextView.text = output1
            case 3:
                //Uncomment the line below for Stretch #2
                output2 = createSentence(name: "Swift")
                myTextView.text = output2
            case 4:
                //Uncomment the line below for Stretch #3
                output3 = createFruitLovingSentence(fruitOne: "apples", fruitTwo: "bananas")
                myTextView.text = output3
            case 5:
                //Uncomment the two lines below for Stretch #4
                let sentence = "Here is my sentence"
                output4 = "\(sentence), has \(countTheCharacters(sentence: sentence)) characters"
                myTextView.text = output4
            case 6:
                //Uncomment the three lines below for Stretch #5
                let sentence = "Here is my sentence"
                let tuple = findVowelsConsonants(sentence: sentence)
                output5 = sentence + ", has:\n" + "Vowels:\(tuple.0) Consonants:\(tuple.1)"
                myTextView.text =  output5
            default:
                //Uncomment the three lines below for Stretch #6
                let sentence = "Here is my sentence"
                let tuple = findVowelsConsonantsPunctuation(sentence: sentence)
                output6 = sentence + ", has:\n" + "Vowels:\(tuple.0) \nConsonants:\(tuple.1) \nPunctuation:\(tuple.2)"
                myTextView.text =  output6
        }
    }
    
    func displayMVP() {
        myTextView.text = "MVP Completed"
    }
    
    /***************************************************
     * Start Your Code Here For Stretch #1
     ***************************************************/
    func returnAString() -> String {
        return "Stretch #1 Completed"
    }
    /***************************************************
     * End Your Code Here For Stretch #1
     ***************************************************/
    
    /***************************************************
    * Start Your Code Here For Stretch #2
    ***************************************************/
    func createSentence(name: String) -> String {
        return "I love programming with \(name)!"
    }
    /***************************************************
    * End Your Code Here For Stretch #2
    ***************************************************/
    
    /***************************************************
    * Start Your Code Here For Stretch #3
    ***************************************************/
    func createFruitLovingSentence(fruitOne: String, fruitTwo: String) -> String {
        return "I love \(fruitOne) and \(fruitTwo)"
    }
    /***************************************************
    * End Your Code Here For Stretch #3
    ***************************************************/
    
    /***************************************************
     * Start Your Code Here For Stretch #4
     ***************************************************/
    func countTheCharacters(sentence: String) -> Int {
        return sentence.characters.count
    }
    /***************************************************
     * End Your Code Here For Stretch #4
     ***************************************************/

    /***************************************************
    * Start Your Code Here For Stretch #5
    ***************************************************/
    func findVowelsConsonants(sentence: String) -> (Int, Int) {
        let vowelConstants = ["a", "e", "i", "o", "u", "y"]
        var vowels = 0
        var consonants = 0
        
        for character in sentence.characters {
            var isVowel = false;
            let charStr = String(character).lowercased()
            for vowel in vowelConstants {
                if (charStr == vowel) {
                    isVowel = true;
                }
            }
            
            if (charStr != " ") {
                if (isVowel) { vowels += 1 }
                else { consonants += 1 }
            }
        }
        
        return (vowels, consonants)
    }
    /***************************************************
    * End Your Code Here For Stretch #5
    ***************************************************/

    /***************************************************
    * Start Your Code Here For Stretch #6
    ***************************************************/
    func findVowelsConsonantsPunctuation(sentence: String) -> (Int, Int, Int) {
        let vowelConstants = ["a", "e", "i", "o", "u", "y"]
        let punctuationConstants = [".", ",", "?", "!", ":", ";", "\"", "'", "(", ")", " "]
        var vowels = 0
        var consonants = 0
        var punctuation = 0
        
        for character in sentence.characters {
            var isVowel = false;
            var isPunc = false;
            
            let charStr = String(character).lowercased()
            for vowel in vowelConstants {
                if (charStr == vowel) {
                    isVowel = true;
                }
            }
                
            for punct in punctuationConstants {
                if (charStr == punct) {
                    isPunc = true;
                }
            }
            
            if (isVowel) { vowels += 1 }
            else if (isPunc) { punctuation += 1 }
            else { consonants += 1 }
        }
        
        return (vowels, consonants, punctuation)
    }
    /***************************************************
    * End Your Code Here For Stretch #6
    ***************************************************/
}

