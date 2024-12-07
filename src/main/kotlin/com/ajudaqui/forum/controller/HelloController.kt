
package com.ajudaqui.forum.controller
import org.springframework.web.bind.annotation.GetMapping

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/hello")
class HellowController {

    @GetMapping("/ha")
    fun sayHello(): String {
        return "Hello, world!"
    }
}
