//
//  Job.swift
//  compositionHackwich
//
//  Created by Achatz, Thomas on 3/11/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import Foundation
class Job {
    var description:String;
    var salary:Int;
    
    init(desc:String, money:Int) {
        self.description = desc;
        self.salary = money;
    }
}
