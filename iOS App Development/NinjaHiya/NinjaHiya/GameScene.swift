//
//  GameScene.swift
//  NinjaHiya
//
//  Created by Achatz, Thomas on 4/4/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import SpriteKit;
import GameplayKit;

struct PhysicsCategory {
    static let none:UInt32 = 0;
    static let all:UInt32 = UInt32.max;
    static let monster:UInt32 = 0b1;
    static let projectile: UInt32 = 0b10;
}

class GameScene:SKScene, SKPhysicsContactDelegate {
    let player = SKSpriteNode(imageNamed: "ninja");
    override func didMove(to view: SKView) {
        physicsWorld.gravity = CGVector.zero;
        physicsWorld.contactDelegate = self;
        
        backgroundColor = SKColor.white;
        player.position = CGPoint(x: size.width * 0.1, y: size.height * 0.5);
        addChild(player);
        run(SKAction.repeatForever(SKAction.sequence([SKAction.run(addMonster), SKAction.wait(forDuration: 0.1)])));
        
        let backgroundMusic = SKAudioNode(fileNamed: "gameMusic.mp3");
        backgroundMusic.autoplayLooped = true;
        addChild(backgroundMusic);
    }
    
    func random() -> CGFloat {
        return CGFloat(Float(arc4random()) / 0xFFFFFFFF);
    }
    
    func random(min:CGFloat, max:CGFloat) -> CGFloat {
        return random() * (max - min) + min;
    }
    
    func addMonster() {
        let monster = SKSpriteNode(imageNamed: "enemy");
        monster.physicsBody = SKPhysicsBody(rectangleOf: monster.size);
        monster.physicsBody?.isDynamic = true;
        monster.physicsBody?.categoryBitMask = PhysicsCategory.monster;
        monster.physicsBody?.collisionBitMask = PhysicsCategory.none;
        
        let actualY = random(min: monster.size.height / 2, max: size.height - monster.size.height/2);
        monster.position = CGPoint(x: size.width + monster.size.width / 2, y: actualY);
        let actualDuration = random(min: CGFloat(2), max: CGFloat(4));
        let actionMove = SKAction.move(to: CGPoint(x: -monster.size.width/2, y: actualY), duration: TimeInterval(actualDuration));
        let actionMoveDone = SKAction.removeFromParent();
        
        addChild(monster);
        
        monster.run(SKAction.sequence([actionMove, actionMoveDone]));
    }
    
    func projectileDidCollide(projectile:SKSpriteNode, monster:SKSpriteNode) {
        projectile.removeFromParent();
        monster.removeFromParent();
    }
    
    func didBegin(_ contact: SKPhysicsContact) {
        let first = contact.bodyA;
        let second = contact.bodyB;
        if let monster = first.node as? SKSpriteNode, let projectile = second.node as? SKSpriteNode {
            projectileDidCollide(projectile: projectile, monster: monster);
        }
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        if let touch = touches.first {
            run(SKAction.playSoundFileNamed("pew.wav", waitForCompletion: false));
            let touchLocation = touch.location(in: self);
            let projectile = SKSpriteNode(imageNamed: "star");
            
            projectile.physicsBody = SKPhysicsBody(circleOfRadius: projectile.size.width / 2);
            projectile.physicsBody?.isDynamic = true;
            projectile.physicsBody?.categoryBitMask = PhysicsCategory.projectile;
            projectile.physicsBody?.contactTestBitMask = PhysicsCategory.monster;
            projectile.physicsBody?.collisionBitMask = PhysicsCategory.none;
            projectile.physicsBody?.usesPreciseCollisionDetection = true;
            
            projectile.position = player.position;
            let offset = touchLocation - (projectile.position);
            if (offset.x < 0) { return }
            let direction = offset.normalized();
            let amount = direction * 1000;
            
            let destination = amount + (projectile.position);
            let actionMove = SKAction.move(to: destination, duration: 2.0);
            let actionMoveDone = SKAction.removeFromParent();
            projectile.run(SKAction.sequence([actionMove, actionMoveDone]));
            
            addChild(projectile);
        }
    }
}
