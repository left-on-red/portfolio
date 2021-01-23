//
//  Person.swift
//  compositionHackwich
//
//  Created by Achatz, Thomas on 3/11/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import Foundation
class Person {
    var name:String;
    var job:Job;
    
    init(n:String, j:Job) {
        self.name = n;
        self.job = j;
    }
}
