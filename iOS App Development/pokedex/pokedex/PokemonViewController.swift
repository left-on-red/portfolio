//
//  PokemonViewController.swift
//  pokedex
//
//  Created by Achatz, Thomas on 5/7/19.
//  Copyright © 2019 Achatz, Thomas. All rights reserved.
//

import UIKit

class PokemonViewController: UIViewController {
    var id:Int = 0;
    var name:String = "";
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var idLabel: UILabel!
    @IBOutlet weak var weightLabel: UILabel!
    @IBOutlet weak var heightLabel: UILabel!
    @IBOutlet weak var dexLabel: UILabel!
    @IBOutlet weak var imageView: UIImageView!
    
    var baseURL = "https://pokeapi.co/api/v2/pokemon";
    var speciesURL = "https://pokeapi.co/api/v2/pokemon-species";
    
    func getData(from url: URL, completion: @escaping (Data?, URLResponse?, Error?) -> ()) {
        URLSession.shared.dataTask(with: url, completionHandler: completion).resume()
    }
    
    func downloadImage(from url: URL) {
        getData(from: url) { data, response, error in
            guard let data = data, error == nil else { return }
            //print(response?.suggestedFilename ?? url.lastPathComponent);
            DispatchQueue.main.async() { self.imageView.image = UIImage(data: data) }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad();
        dexLabel.numberOfLines = 10;
        nameLabel.text = name;
        idLabel.text = "#\(id)";
        let request = URLRequest(url: NSURL(string: "\(baseURL)/\(id)")! as URL);
        do {
            let response:AutoreleasingUnsafeMutablePointer<URLResponse?>? = nil;
            let data = try NSURLConnection.sendSynchronousRequest(request, returning: response);
            let jsonSerialized = try JSONSerialization.jsonObject(with: data, options: []) as? [String:Any];
            if let json = jsonSerialized {
                let weight = json["weight"] as! Int;
                let height = json["height"] as! Int;
                //let weightStr = String(weight).insert(contentsOf: ".", at: String(weight).count - 2);
                var weightStr = String(weight);
                weightStr.insert(".", at: weightStr.index(before: weightStr.endIndex));
                var heightStr = String(height);
                heightStr.insert(".", at: heightStr.index(before: heightStr.endIndex));
                weightLabel.text = "\(weightStr)kg";
                heightLabel.text = "\(heightStr)m";
                let sprites = json["sprites"] as? [String:Any];
                let imageURL = URL(string: sprites?["front_default"] as! String)!
                downloadImage(from: imageURL);
            }
        }
        
        catch let error { print(error) }
        
        let speciesRequest = URLRequest(url: NSURL(string: "\(speciesURL)/\(id)")! as URL);
        do {
            let response:AutoreleasingUnsafeMutablePointer<URLResponse?>? = nil;
            let data = try NSURLConnection.sendSynchronousRequest(speciesRequest, returning: response);
            let jsonSerialized = try JSONSerialization.jsonObject(with: data, options: []) as? [String:Any];
            if let json = jsonSerialized {
                //print(json);
                let flavorText = json["flavor_text_entries"] as? NSArray;
                //let english = flavorText?[1] as? [String:Any];
                //let text = english?["flavor_text"] as? String;
                var text = "";
                for item in flavorText! {
                    let prop = item as! [String:Any];
                    let subprop = prop["language"] as! [String:Any];
                    let language = subprop["name"] as! String;
                    if (language == "en") { text = (prop["flavor_text"] as? String)!; break; }
                }
                //let english = flavorText?[1] as! [String:Any];
                //let text = english["flavor_text"] as? String;
                
                //let weightStr = String(weight).insert(contentsOf: ".", at: String(weight).count - 2);
                dexLabel.text = text;
            }
        }
        
        catch let error { print(error) }
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
