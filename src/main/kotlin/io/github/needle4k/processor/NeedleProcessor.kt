package io.github.needle4k.processor

import io.github.needle4k.NeedleContext

interface NeedleProcessor {
  /**
   * @param context needle context for test class
   */
  fun process(context: NeedleContext)
}