//
//  ViewController.swift
//  CMPE273Rpi
//
//  Created by Arjun Shukla on 4/18/15.
//  Copyright (c) 2015 CMPE273. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate, UITextViewDelegate {
    
    @IBOutlet weak var lblConsole: UILabel!
    @IBOutlet weak var txtConsole: UITextView!
    @IBOutlet weak var lblConsoleHeader: UILabel!
    @IBOutlet weak var btnAutoSYnc: UISwitch!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        txtConsole.delegate = self
        txtConsole.editable = false
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func onSync(sender: AnyObject) {
        //        let url = NSURL(string: "http://192.168.137.220:8080/illuminati/authorize")
        let url = NSURL(string: "https://jaxirdyw.p6.weaved.com/illuminati/upload")
        
        
        let task = NSURLSession.sharedSession().dataTaskWithURL(url!) {(data, response, error) in
            println(NSString(data: data, encoding: NSUTF8StringEncoding))
            let strConsoleLog = NSString(data:data, encoding:NSUTF8StringEncoding)
            
            dispatch_sync(dispatch_get_main_queue())
                {
                    /* Do UI work here */
                    self.txtConsole.text = self.txtConsole.text! + "\n---------------\n" + String(strConsoleLog!)
            }
            
            
        }
        
        task.resume()
        self.txtConsole.hidden = false
        self.lblConsoleHeader.hidden = false
    }
    
    
    @IBAction func onDownload(sender: AnyObject) {
        let url = NSURL(string: "https://jaxirdyw.p6.weaved.com/illuminati/download")
        
        
        let task = NSURLSession.sharedSession().dataTaskWithURL(url!) {(data, response, error) in
            println(NSString(data: data, encoding: NSUTF8StringEncoding))
            let strConsoleLog = NSString(data:data, encoding:NSUTF8StringEncoding)
            dispatch_sync(dispatch_get_main_queue())
                {
                    /* Do UI work here */
                    self.txtConsole.text = self.txtConsole.text! + "\n---------------\n" + String(strConsoleLog!)
            }
            
        }
        
        task.resume()
        self.txtConsole.hidden = false
        self.lblConsoleHeader.hidden = false
    }
    
    @IBAction func onSyncDBPD(sender: AnyObject) {
        let url = NSURL(string: "https://jaxirdyw.p6.weaved.com/illuminati/compareMetaData")
        
        
        let task = NSURLSession.sharedSession().dataTaskWithURL(url!) {(data, response, error) in
            println(NSString(data: data, encoding: NSUTF8StringEncoding))
            let strConsoleLog = NSString(data:data, encoding:NSUTF8StringEncoding)
            dispatch_sync(dispatch_get_main_queue())
                {
                    /* Do UI work here */
                    self.txtConsole.text = self.txtConsole.text! + "\n---------------\n" + String(strConsoleLog!)
            }
            
        }
        
        task.resume()
        self.txtConsole.hidden = false
        self.lblConsoleHeader.hidden = false
    }
    
    
    
    @IBAction func onAutoSync(sender: UISwitch) {
        if btnAutoSYnc.on {
            let url = NSURL(string: "https://jaxirdyw.p6.weaved.com/Scheduler/Start")
            
            
            let task = NSURLSession.sharedSession().dataTaskWithURL(url!) {(data, response, error) in
                println(NSString(data: data, encoding: NSUTF8StringEncoding))
                let strConsoleLog = NSString(data:data, encoding:NSUTF8StringEncoding)
                dispatch_sync(dispatch_get_main_queue())
                    {
                        /* Do UI work here */
                        self.txtConsole.text = self.txtConsole.text! + "\n---------------\n" + String(strConsoleLog!)
                }
                
            }
            
            task.resume()
            self.txtConsole.hidden = false
            self.lblConsoleHeader.hidden = false
        } else {
            let url = NSURL(string: "")//https://wxlekwzj.p6.weaved.com/illuminati/authorize")
            
            
            let task = NSURLSession.sharedSession().dataTaskWithURL(url!) {(data, response, error) in
                println(NSString(data: data, encoding: NSUTF8StringEncoding))
                let strConsoleLog = NSString(data:data, encoding:NSUTF8StringEncoding)
                dispatch_sync(dispatch_get_main_queue())
                    {
                        /* Do UI work here */
                        self.txtConsole.text = self.txtConsole.text! + "\n---------------\n" + String(strConsoleLog!)
                }
                
            }
            
            task.resume()
            
            self.txtConsole.hidden = false
            self.lblConsoleHeader.hidden = false
        }
        
        
    }
    
}