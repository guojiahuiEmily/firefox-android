<!-- AUTOGENERATED BY glean_parser.  DO NOT EDIT. -->

# Metrics

This document enumerates the metrics collected by this project using the [Glean SDK](https://mozilla.github.io/glean/book/index.html).
This project may depend on other projects which also collect metrics.
This means you might have to go searching through the dependency tree to get a full picture of everything collected by this project.

# Pings

- [events](#events)

## events

This is a built-in ping that is assembled out of the box by the Glean SDK.

See the Glean SDK documentation for the [`events` ping](https://mozilla.github.io/glean/book/user/pings/events.html).

All Glean pings contain built-in metrics in the [`ping_info`](https://mozilla.github.io/glean/book/user/pings/index.html#the-ping_info-section) and [`client_info`](https://mozilla.github.io/glean/book/user/pings/index.html#the-client_info-section) sections.

In addition to those built-in metrics, the following metrics are added to the ping:

| Name | Type | Description | Data reviews | Extras | Expiration | [Data Sensitivity](https://wiki.mozilla.org/Firefox/Data_Collection) |
| --- | --- | --- | --- | --- | --- | --- |
| nimbus_events.disqualification |[event](https://mozilla.github.io/glean/book/user/metrics/event.html) |Recorded when a user becomes ineligible to continue receiving the treatment for an enrolled experiment, for reasons such as the user opting out of the experiment or no longer matching targeting for the experiment.  |[mozilla-mobile/android-components#9168](https://github.com/mozilla-mobile/android-components/pull/9168#issuecomment-743461975)|<ul><li>branch: The branch slug/identifier that was randomly chosen</li><li>enrollment_id: A unique identifier generated at enrollment time</li><li>experiment: The slug/unique identifier of the experiment</li></ul>|never |1 |
| nimbus_events.enrollment |[event](https://mozilla.github.io/glean/book/user/metrics/event.html) |Recorded when a user has met the conditions and is first bucketed into an experiment (i.e. targeting matched and they were randomized into a bucket and branch of the experiment). Expected a maximum of once per experiment per user.  |[mozilla-mobile/android-components#9168](https://github.com/mozilla-mobile/android-components/pull/9168#issuecomment-743461975)|<ul><li>branch: The branch slug/identifier that was randomly chosen</li><li>enrollment_id: A unique identifier generated at enrollment time</li><li>experiment: The slug/unique identifier of the experiment</li></ul>|never |1 |
| nimbus_events.exposure |[event](https://mozilla.github.io/glean/book/user/metrics/event.html) |Recorded when a user actually observes an experimental treatment, or would have observed an experimental treatment if they had been in a branch that would have shown one.  |[mozilla-mobile/android-components#9168](https://github.com/mozilla-mobile/android-components/pull/9168#issuecomment-743461975)|<ul><li>branch: The branch slug/identifier that was randomly chosen</li><li>enrollment_id: A unique identifier generated at enrollment time</li><li>experiment: The slug/unique identifier of the experiment</li></ul>|never |1 |
| nimbus_events.unenrollment |[event](https://mozilla.github.io/glean/book/user/metrics/event.html) |Recorded when either telemetry is disabled, or the experiment has run for its designed duration (i.e. it is no longer present in the Nimbus Remote Settings collection)  |[mozilla-mobile/android-components#9168](https://github.com/mozilla-mobile/android-components/pull/9168#issuecomment-743461975)|<ul><li>branch: The branch slug/identifier that was randomly chosen</li><li>enrollment_id: A unique identifier generated at enrollment time</li><li>experiment: The slug/unique identifier of the experiment</li></ul>|never |1 |

Data categories are [defined here](https://wiki.mozilla.org/Firefox/Data_Collection).

<!-- AUTOGENERATED BY glean_parser.  DO NOT EDIT. -->
