package grails.plugin.recursivemessages;

import org.codehaus.groovy.grails.context.support.PluginAwareResourceBundleMessageSource;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang.text.*;

/**
 * Extension of the default Grails message source that uses commons-lang
 * ExtendedMessageFormat instead of the core MessageFormat in order to support
 * recursive resolution of {0,message,prefix.} placeholders.
 */
public class RecursiveMessageSource extends PluginAwareResourceBundleMessageSource {

  private Map<String, FormatFactory> registry;
 
  public RecursiveMessageSource() {
    registry = new HashMap<String, FormatFactory>();
    registry.put("message", new RecursiveFormatFactory());
  }

  protected MessageFormat createMessageFormat(String msg, Locale locale) {
    return new ExtendedMessageFormat((msg != null) ? msg : "", locale, registry);
  }

  public class RecursiveFormatFactory implements FormatFactory {
    public Format getFormat(String name, String args, Locale locale) {
      return new RecursiveFormat(args, locale);
    }
  }

  /**
   * Format that formats a given string by adding a configured prefix, treating
   * the result as a message key and looking it up recursively in the current
   * messageSource.
   */
  public class RecursiveFormat extends Format {
    private String prefix;
    private Locale locale;

    public RecursiveFormat(String prefix, Locale locale) {
      this.prefix = (prefix != null ? prefix : "");
      this.locale = locale;
    }

    public Object parseObject(String source, ParsePosition pos) {
      throw new UnsupportedOperationException("RecursiveFormat does not support parsing");
    }

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
      toAppendTo.append(getMessage(prefix + obj, new Object[0], (String)obj, locale));
      return toAppendTo;
    }
  }
}
