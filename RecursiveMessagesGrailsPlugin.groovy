class RecursiveMessagesGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.0 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]

    // must load after the core i18n plugin as we modify the messageSource that
    // it defines rather than creating our own bean
    def loadAfter = ['i18n']

    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Recursive Messages Plugin" // Headline display name of the plugin
    def author = "Ian Roberts"
    def authorEmail = "i.roberts@dcs.shef.ac.uk"
    def description = '''\
Plugin providing a "recursive" messageSource that supports i18n message
patterns that include placeholders like "{0,message,prefix.}".  When such a
message is resolved, the value for such a placeholder is not substituted
verbatim in the usual way, but instead is treated as another message lookup key
(with the specified prefix prepended).
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/recursive-messages"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.grails-plugins.codehaus.org/browse/grails-plugins/" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // modify the default messageSource to use our subclass
        def messageSourceBeanDef = delegate.getBeanDefinition("messageSource")
        messageSourceBeanDef.beanClass = grails.plugin.recursivemessages.RecursiveMessageSource
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
