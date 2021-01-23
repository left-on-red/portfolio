//
//  GameViewController.swift
//  NinjaHiya
//
//  Created by Achatz, Thomas on 4/4/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit
import SpriteKit
import GameplayKit

class GameViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad();
        
        let scene = GameScene(size: view.bounds.size);
        let SKView = view as! SKView;
        SKView.showsFPS = true;
        SKView.showsNodeCount = true;
        SKView.ignoresSiblingOrder = true;
        scene.scaleMode = .resizeFill;
        SKView.presentScene(scene);
    }
    
    override var prefersStatusBarHidden: Bool { return true }
}
