recursive-messages Grails plugin
================================

This is a Grails plugin that implements "recursive" message resolution.  It
extends the default message format placeholder handling to allow placeholders
whose replacement strings are themselves looked up in the messageSource rather
than simply inserted verbatim.  The motivating use case is for domain class
property names in validation messages:

    default.blank.message={0,message,property.name.} must not be blank
    property.name.firstName=First name
    property.name.email=E-mail address

In this example, a domain class with a firstName property that is constrained
to be non-blank would produce the error message "First name must not be blank".

The mechanism looks for message placeholders that consist of an argument index,
comma, "message", comma, and a prefix.  When substituting a value `v` for the
placeholder the plugin will look up the message key `prefix + v` with no
arguments, and use the result of that lookup as the placeholder replacement.
If there is no message named `prefix + v` then `v` is used verbatim in the
normal way.
