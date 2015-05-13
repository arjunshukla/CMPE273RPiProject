//
//  ViewController.swift
//  CMPE273Rpi
//
//  Created by Arjun Shukla on 4/18/15.
//  Copyright (c) 2015 CMPE277. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var lblConsole: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


    @IBAction func onSync(sender: AnyObject) {
//        let url = NSURL(string: "http://192.168.137.220:8080/illuminati/authorize")
        let url = NSURL(string: "https://wxlekwzj.p6.weaved.com/illuminati/authorize")
        
        
        let task = NSURLSession.sharedSession().dataTaskWithURL(url!) {(data, response, error) in
            println(NSString(data: data, encoding: NSUTF8StringEncoding))
            let strConsoleLog = NSString(data:data, encoding:NSUTF8StringEncoding)
            self.lblConsole.text = strConsoleLog as String!
        }
        
        task.resume()
    }

    
}

